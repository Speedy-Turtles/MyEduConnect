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
    }
}