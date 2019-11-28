import { User } from '../models/User';
import { Documents } from '../models/Documents';
import { Transaction } from '../models/Transaction';
import { Categories } from '../models/Categories';

export class CompleteIdeaDTO{

    id:  number;
    name: string;
    objective: string;
    explanation: string;
    contact: string;
    country: string;
    state: string;
    createdAt: Date;
    campaignFinishedDate: Date;
    description: string;
    video: string;
    principalImage: string;
    entrepreneurs: User[];
    documents: Documents[];
    transaction: Transaction[];
    categories: Categories[];
    
    fundRaised: number;
    progress: number;
    suports: number;
}