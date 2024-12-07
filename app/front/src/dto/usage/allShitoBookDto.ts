import Sheet0801Interface from "./sheet0801Dto";
import Sheet0801Dto from "./sheet0801Dto";

export default interface AllShitoBookInterface{

}

export default class AllShitoBookDto implements AllShitoBookInterface{

    /** 様式8の1 */
    sheet0801Dto:Sheet0801Interface;

    constructor(){
        this.sheet0801Dto = new Sheet0801Dto();
    }
}