import Axios from "axios";

import "@/plugins/axios"

export default{
    getALLRoles(){
        return Axios.get("GetAllRole");
    },
    getRoleById(id){
        return Axios.get("getRoleByid?id="+id);
    },
    TestEmail(email){
        return Axios.get("ExistMail?email="+email);
    },
    TestExistcode(code){
        return Axios.get("ExistToken?code="+code);
    },
    updateWelcome(email){
        return Axios.post("updateWelcome?email="+email);
    },
    async GetUsers(){
        const response=await Axios.get("getUsers");
        return response;
    },
    UpdateStatus(info){
        return Axios.post("UpdateStatus",info);
    },
    getUserAuthentifie(){
        return Axios.get("getUserAuthentifie");
    }
}