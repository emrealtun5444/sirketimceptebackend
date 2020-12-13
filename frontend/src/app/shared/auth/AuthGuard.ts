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
                if (this.hasAuthorization(route.data.authorization)) return true;
                this.router.navigate(['/access'], {queryParams: {returnUrl: state.url}});
            }
            return true;
        }
        // not logged in so redirect to login page with the return url
        this.router.navigate(['/login'], {queryParams: {returnUrl: state.url}});
        return false;
    }

    hasAuthorization(authLink): boolean {
        const authorizations = authLink.split(',');
        for (const val of authorizations) {
            const hasAuthorization = this.tokenStorage.hasAuthorization(val);
            if (hasAuthorization) return true;
        }
        return false;
    }
}
