import Axios from "axios";
import "@/plugins/axios"

export default{
    uploadPhoto(data){
        return Axios.post("uploadPhoto",data);
    },
    EditInfoPersonnel(data){
        return Axios.post("EditInfoPersonnel",data);
    },
    CheckPassword(val){
        return Axios.get("CheckPassword?password="+val);
    },
    ChangerPasswordActuel(val){ 
        return Axios.post("ChangerPasswordActuel?password="+val);
    },
    SendChangedEmail(val){  
        return Axios.get("SendChangedEmail?email="+val);
    },
    updateEmail(info){
        return Axios.post("updateEmail",info);
    }
}