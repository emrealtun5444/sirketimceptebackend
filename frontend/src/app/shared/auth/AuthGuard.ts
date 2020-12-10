import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {TokenStorageService} from '../service/token-storage.service';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router,
                private tokenStorage: TokenStorageService) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        if (this.tokenStorage.getToken()) {
            if (route.data.role) {
                if (this.hasRole(route.data.role)) return true;
                this.router.navigate(['/access'], {queryParams: {returnUrl: state.url}});
            }
            return true;
        }
        // not logged in so redirect to login page with the return url
        this.router.navigate(['/login'], {queryParams: {returnUrl: state.url}});
        return false;
    }

    hasRole(authLink): boolean {
        let roles = authLink.split(',');
        for (var val of roles) {
            let hasRole = this.tokenStorage.hasRole(val);
            if (hasRole) return true;
        }
        return false;
    }
}
