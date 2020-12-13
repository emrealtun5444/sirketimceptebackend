import {SelectItem} from "primeng/api";

export class User {
  id: number;
  name: string;
  surname: string;
  username: string;
  email: string;
  authorizations: string[];
  companies: SelectItem[];
}
