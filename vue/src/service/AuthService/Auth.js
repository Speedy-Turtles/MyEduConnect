import axios from "axios";
import  "@/plugins/axios";

export default{

    StoreUser(user){
        let data=new FormData();
        data.append("firstname",user.firstname);
        data.append("lastname",user.lastname);
        data.append("email",user.email);
        data.append("birth_day",user.birthday);
        data.append("password",user.password);
        data.append("Role_name",user.Role_name);
        data.append("photo",user.photo);
        data.append("sex",user.sex);
        data.append("cin",user.cin);
        data.append("num_tlf",user.num_tlf);
        data.append("classe_id",user.classe_id);
        const config={
            Headers:{
                "content-type":"multipart/form-data",
            },
        };
        console.log(user);
        return axios.post("auth/signup",data,config)
    }

}