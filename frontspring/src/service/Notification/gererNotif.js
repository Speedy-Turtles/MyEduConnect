import Axios from "axios";

import "@/plugins/axios"

export default{
    getNotifs(){
        return Axios.get("GetNotif");
    },
    updateNotif(){
        return Axios.post("updateNotif");
    },
    deleteNotificationById(id){
        return Axios.delete("DeleteNotificationById?id="+id);
    },
    deleteAllNotif(){
        return Axios.delete("DeleteAllNotif");
    },
    getNotifNotSeen(){
        return Axios.get("GetNotifNotSeen");
    }
}