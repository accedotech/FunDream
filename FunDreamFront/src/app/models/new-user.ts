import { Idea } from './idea';

export class NewUser {

    firstName: string;
    lastName: string;    
    email: string;
    password: string;
    country: string;    
    birthdate: Date;    
    image: string;        
    roles: string[];        
    ideas: Idea[];

}
