package com.aymer.sirketimceptebackend.report.api;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.cariKart.repository.CariKartRepository;
import com.aymer.sirketimceptebackend.common.api.BaseController;
import com.aymer.sirketimceptebackend.common.api.dto.AppResponse;
import com.aymer.sirketimceptebackend.report.dto.*;
import com.aymer.sirketimceptebackend.report.service.performansreport.PerformansReportService;
import com.aymer.sirketimceptebackend.system.user.dto.UserListItem;
import com.aymer.sirketimceptebackend.system.user.model.User;
import com.aymer.sirketimceptebackend.system.user.service.UserService;
import com.aymer.sirketimceptebackend.utils.SessionUtils;
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

    private final PerformansReportService performansReportService;
    private final UserService userService;
    private final SessionUtils sessionUtils;
    private final CariKartRepository cariKartRepository;

    @Autowired
    public ReportController(PerformansReportService performansReportService, UserService userService, SessionUtils sessionUtils, CariKartRepository cariKartRepository) {
        this.performansReportService = performansReportService;
        this.userService = userService;
        this.sessionUtils = sessionUtils;
        this.cariKartRepository = cariKartRepository;
    }

    @GetMapping("/loadPerformansOzet/{year}")
    @PreAuthorize("hasAuthority('PERFORMANS_REPORT_MENU')")
    public ResponseEntity<?> loadPerformansReportOzet(@Valid @PathVariable(name = "year") Integer yil) {
        PerformansOzetDto performansOzetDto = performansReportService.preparePerformansOzet(yil);
        return ResponseEntity.ok(new AppResponse(performansOzetDto));
    }

    @GetMapping("/loadCiroDagilim/{year}/{userName}/{cariKart}")
    @PreAuthorize("hasAuthority('PERFORMANS_REPORT_MENU')")
    public ResponseEntity<?> loadCiroDagilim(@Valid @PathVariable(name = "year") Integer year, @PathVariable(name = "userName") String userName, @PathVariable(name = "cariKart") String cariKart) {
        User user = getUser(userName);
        CariKart ck = getCariKart(cariKart);
        List<HedefDto> ciroDtoList = performansReportService.donemeGoreCiroDagilimi(user, year, ck);
        return ResponseEntity.ok(new AppResponse(ciroDtoList));
    }

    @GetMapping("/loadTahsilatDagilim/{year}/{userName}/{cariKart}")
    @PreAuthorize("hasAuthority('PERFORMANS_REPORT_MENU')")
    public ResponseEntity<?> loadTahsilatDagilim(@Valid @PathVariable(name = "year") Integer year, @PathVariable(name = "userName") String userName, @PathVariable(name = "cariKart") String cariKart) {
        User user = getUser(userName);
        CariKart ck = getCariKart(cariKart);
        List<TahsilatDto> tahsilatDtos = performansReportService.donemeGoreTahsilatDagilimi(user, year, ck);
        return ResponseEntity.ok(new AppResponse(tahsilatDtos));
    }

    @GetMapping("/loadSiparisDagilim/{year}/{userName}/{cariKart}")
    @PreAuthorize("hasAuthority('PERFORMANS_REPORT_MENU')")
    public ResponseEntity<?> loadSiparisDagilim(@Valid @PathVariable(name = "year") Integer year, @PathVariable(name = "userName") String userName, @PathVariable(name = "cariKart") String cariKart) {
        User user = getUser(userName);
        CariKart ck = getCariKart(cariKart);
        List<SiparisDto> siparisDtoList = performansReportService.donemeGoreSiparisDagilimi(user, year, ck);
        return ResponseEntity.ok(new AppResponse(siparisDtoList));
    }

    @GetMapping("/loadMarkaDagilim/{year}/{userName}/{cariKart}")
    @PreAuthorize("hasAuthority('PERFORMANS_REPORT_MENU')")
    public ResponseEntity<?> loadMarkaDagilim(@Valid @PathVariable(name = "year") Integer year, @PathVariable(name = "userName") String userName, @PathVariable(name = "cariKart") String cariKart) {
        User user = getUser(userName);
        CariKart ck = getCariKart(cariKart);
        List<MarkaDagilimDto> markaDagilimDtos = performansReportService.donemeGoreMarkaDagilimi(user, year, ck);
        return ResponseEntity.ok(new AppResponse(markaDagilimDtos));
    }

    @GetMapping("/loadHedefCariDagilim/{year}/{userName}")
    @PreAuthorize("hasAuthority('PERFORMANS_REPORT_MENU')")
    public ResponseEntity<?> loadHedefCariDagilim(@Valid @PathVariable(name = "year") Integer year, @PathVariable(name = "userName") String userName) {
        User user = getUser(userName);
        List<HedefCariDto> hedefCariDtoList = performansReportService.donemeGoreHedefCariDagilimi(user, year, sessionUtils.getSelectedCompany());
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

    private CariKart getCariKart(@PathVariable(name = "cariKart") String cariKart) {
        CariKart ck = null;
        if (!cariKart.equalsIgnoreCase("all")) {
            return cariKartRepository.findByCariKodu(cariKart);
        }
        return ck;
    }

}
