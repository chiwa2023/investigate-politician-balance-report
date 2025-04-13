export default interface FrameworkTaskPlanInterface{

}

export default class FrameworkTaskPlanDto implements FrameworkTaskPlanInterface{
        /** 表示メッセージ */
        message: string;

        /** 取得コード */
        taskPlanCode: number;
    
        /** 登録年 */
        year: number;

        constructor(){

            const INIT_NUMBER: number = 0;
            const INIT_STRING: string = "";
            
            this.message = INIT_STRING;
            this.taskPlanCode = INIT_NUMBER;
            this.year = INIT_NUMBER;
    
        }
    
}