import axios from "axios";
import "@/plugins/axios";

export default{
    uploadPhoto(contenu){
        console.log(contenu.photo);
        let data=new FormData();
        data.append("photo",contenu.photo);
        const config = {
            Headers: {
                "content-type": "multipart/form-data",
            },
        };
        return axios.post("edit/uploadPhoto",data,config);
    },
    EditInfoPersonnel(user){
        return axios.put("edit/EditInfoPersonnel",user);
    },
    CheckPassword(password){
        return axios.get("edit/CheckPassword/"+password);
    },
    ChangerPasswordActuel(password){
        return axios.put("edit/ChangerPasswordActuel/"+password);
    },
    SendChangedEmail(email){
        return axios.get("edit/SendChangedEmail/"+email);
    },
    updateEmail(data){
        return axios.put("edit/updateEmail",data);
    }
}