export interface ChangePasswordRequest {
  idUsuario: number;
  actualPassword: string;
  newPassword: string;
  confirmPassword: string;
}
