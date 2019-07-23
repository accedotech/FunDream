import { User } from './User';
import { Categories } from './Categories';
import { Documents } from './Documents';


export class Idea {

    id: number;
    name: string;
    objective: string;
    explanation: string;
    contact: string;
    country: string;
    state: string;
    createdAt: Date;
    principalImage: string; 
    entrepreneurs: User[];
    categories: Categories[];
    documents: Documents[];
}
