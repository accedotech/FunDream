import { User } from '../models/User';
import { Categories } from '../models/Categories';

export class IdeaHomeDTO{
    id: number;
    name: string;
    objective: string;
    country: string;
    description: string;
    principalImage: string;
    entrepreneurs: User[];
    categories: Categories[];
    
    fundRaised: number;
    progress: number;
}