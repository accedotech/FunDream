import { Transaction } from './Transaction';

export class TransactionDebito extends Transaction{

    banco: string;
    tipoCliente: string;;
    tipoIdentificacion: string;;
    numeroIdentificacion: string;;
    numeroCuenta: string;;
}