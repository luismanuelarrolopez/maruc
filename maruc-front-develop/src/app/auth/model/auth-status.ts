import { Role } from "./role";

export interface AuthStatus {
    isAuthenticated: boolean;
    userRole: Role;
    userEmail: string;
    token: string;
    userId: number;
    nombre: string;
}
