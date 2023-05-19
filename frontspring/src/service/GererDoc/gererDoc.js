import Axios from "axios";

import "@/plugins/axios"

export default{
    getDoc(){
        return Axios.get("getDoc");
    },
    ChangerEtat(documentid,userid,etat){
        return Axios.post("ChangerEtat?id="+documentid+"&idUser="+userid+"&etat="+etat);
    },
    DeleteUserDoc(documentid,userid){
        return Axios.delete("DeleteUserDoc?id="+documentid+"&idUser="+userid);
    }
}