import Axios from "axios";

import "@/plugins/axios"

export default{
    addMessage(message){
        return Axios.post("AddMessage?message="+message)
    },
    Getchat(){
        return Axios.get("GetAllMessages");
    },
    AllUserChat(){
        return Axios.get("AllUserChat");
    },
    AllChatPrivate(id){
        return Axios.get("AllChatPrivate?idrecu="+id);
    },
    getUserByID(id){
        return Axios.get("getUserByID?id="+id);
    },
    AddMessagePrivate(id,message){
        return Axios.post("AddMessagePrivate?id="+id+"&message="+message);
    }
}