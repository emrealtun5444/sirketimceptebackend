import {environment} from '../../environments/environment';

export abstract class AbstractService {
  readonly BASE_URL = environment.URL;
}
