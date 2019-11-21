import { Transaction } from './Transaction';

export class TransactionCredito extends Transaction{
    
    numeroTarjeta: string;        
    nombreTitular: string;;
    fechaVnecimiento: Date;        
    nroVerificacion: string;;
}