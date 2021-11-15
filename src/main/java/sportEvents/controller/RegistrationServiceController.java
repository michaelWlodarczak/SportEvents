package sportEvents.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sportEvents.service.UserMaintenanceService;
import sportEvents.service.UserRegisterService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegistrationServiceController {

    @NonNull
    private final UserRegisterService userRegisterService;
    @NonNull
    private final UserMaintenanceService userMaintenanceService;


}
