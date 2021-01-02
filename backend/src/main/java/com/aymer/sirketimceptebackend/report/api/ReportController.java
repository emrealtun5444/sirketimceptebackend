package com.aymer.sirketimceptebackend.report.api;

import com.aymer.sirketimceptebackend.common.api.BaseController;
import com.aymer.sirketimceptebackend.common.api.dto.AppResponse;
import com.aymer.sirketimceptebackend.report.dto.PerformansOzetDto;
import com.aymer.sirketimceptebackend.report.service.ReportService;
import com.aymer.sirketimceptebackend.system.user.dto.UserListItem;
import com.aymer.sirketimceptebackend.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/loadPerformansOzet/{yil}")
    @PreAuthorize("hasAuthority('PERFORMANS_REPORT_MENU')")
    public ResponseEntity<?> loadPerformansReportOzet(@Valid @PathVariable(name = "yil") Integer yil) {
        PerformansOzetDto performansOzetDto = reportService.preparePerformansOzet(yil);
        return ResponseEntity.ok(new AppResponse(performansOzetDto));
    }

    @GetMapping("/loadPerformansReportData/{donem}/{userName}")
    @PreAuthorize("hasAuthority('PERFORMANS_REPORT_MENU')")
    public ResponseEntity<?> loadPerformansReportData(@Valid @PathVariable(name = "donem") String donem, @PathVariable(name = "userName") String userName) {


        return ResponseEntity.ok(new AppResponse(null));
    }

    @GetMapping("/staffs")
    @PreAuthorize("hasAuthority('REPORT_MENU')")
    public ResponseEntity<?> loadStaffs() {
        List<UserListItem> userListItems = userService.grantedUsers();
        return ResponseEntity.ok(new AppResponse(userListItems));
    }

}
