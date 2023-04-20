import axios from "axios";
import  "@/plugins/axios";

export default{
getnotif(){
    return axios.get('notif/getnotif');
},
shownotif(){
    return axios.post('notif/ShowNotif');
}
}