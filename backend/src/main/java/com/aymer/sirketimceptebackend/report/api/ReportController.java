package com.aymer.sirketimceptebackend.report.api;

import com.aymer.sirketimceptebackend.common.api.BaseController;
import com.aymer.sirketimceptebackend.common.api.dto.AppResponse;
import com.aymer.sirketimceptebackend.report.dto.*;
import com.aymer.sirketimceptebackend.report.service.ReportService;
import com.aymer.sirketimceptebackend.system.user.dto.UserListItem;
import com.aymer.sirketimceptebackend.system.user.model.User;
import com.aymer.sirketimceptebackend.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:00
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = BaseController.BASE_PATH + "/report")
public class ReportController {

    private final ReportService reportService;
    private final UserService userService;

    @Autowired
    public ReportController(ReportService reportService, UserService userService) {
        this.reportService = reportService;
        this.userService = userService;
    }

    @GetMapping("/loadPerformansOzet/{year}")
    @PreAuthorize("hasAuthority('PERFORMANS_REPORT_MENU')")
    public ResponseEntity<?> loadPerformansReportOzet(@Valid @PathVariable(name = "year") Integer yil) {
        PerformansOzetDto performansOzetDto = reportService.preparePerformansOzet(yil);
        return ResponseEntity.ok(new AppResponse(performansOzetDto));
    }

    @GetMapping("/loadCiroDagilim/{year}/{userName}")
    @PreAuthorize("hasAuthority('PERFORMANS_REPORT_MENU')")
    public ResponseEntity<?> loadCiroDagilim(@Valid @PathVariable(name = "year") Integer year, @PathVariable(name = "userName") String userName) {
        User user = getUser(userName);
        List<HedefDto> ciroDtoList = reportService.donemeGoreCiroDagilimi(user, year);
        return ResponseEntity.ok(new AppResponse(ciroDtoList));
    }

    @GetMapping("/loadTahsilatDagilim/{year}/{userName}")
    @PreAuthorize("hasAuthority('PERFORMANS_REPORT_MENU')")
    public ResponseEntity<?> loadTahsilatDagilim(@Valid @PathVariable(name = "year") Integer year, @PathVariable(name = "userName") String userName) {
        User user = getUser(userName);
        List<TahsilatDto> tahsilatDtos = reportService.donemeGoreTahsilatDagilimi(user, year);
        return ResponseEntity.ok(new AppResponse(tahsilatDtos));
    }

    @GetMapping("/loadSiparisDagilim/{year}/{userName}")
    @PreAuthorize("hasAuthority('PERFORMANS_REPORT_MENU')")
    public ResponseEntity<?> loadSiparisDagilim(@Valid @PathVariable(name = "year") Integer year, @PathVariable(name = "userName") String userName) {
        User user = getUser(userName);
        List<SiparisDto> siparisDtoList = reportService.donemeGoreSiparisDagilimi(user, year);
        return ResponseEntity.ok(new AppResponse(siparisDtoList));
    }

    @GetMapping("/loadMarkaDagilim/{year}/{userName}")
    @PreAuthorize("hasAuthority('PERFORMANS_REPORT_MENU')")
    public ResponseEntity<?> loadMarkaDagilim(@Valid @PathVariable(name = "year") Integer year, @PathVariable(name = "userName") String userName) {
        User user = getUser(userName);
        List<MarkaDagilimDto> markaDagilimDtos = reportService.donemeGoreMarkaDagilimi(user, year);
        return ResponseEntity.ok(new AppResponse(markaDagilimDtos));
    }

    @GetMapping("/loadHedefCariDagilim/{year}/{userName}")
    @PreAuthorize("hasAuthority('PERFORMANS_REPORT_MENU')")
    public ResponseEntity<?> loadHedefCariDagilim(@Valid @PathVariable(name = "year") Integer year, @PathVariable(name = "userName") String userName) {
        User user = getUser(userName);
        List<HedefCariDto> hedefCariDtoList = reportService.donemeGoreHedefCariDagilimi(user, year);
        return ResponseEntity.ok(new AppResponse(hedefCariDtoList));
    }

    @GetMapping("/staffs")
    @PreAuthorize("hasAuthority('REPORT_MENU')")
    public ResponseEntity<?> loadStaffs() {
        List<UserListItem> userListItems = userService.grantedUsers();
        return ResponseEntity.ok(new AppResponse(userListItems));
    }

    private User getUser(@PathVariable(name = "userName") String userName) {
        User user = null;
        if (!userName.equalsIgnoreCase("all")) {
            Optional<User> userOptional = userService.findByUsername(userName);
            if (userOptional.isPresent())
                user = userOptional.get();
        }
        return user;
    }

}
