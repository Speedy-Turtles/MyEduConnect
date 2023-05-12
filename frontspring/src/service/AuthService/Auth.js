import Axios from "axios";
import {AuthUser} from '@/store/AuthStore.js';
import "@/plugins/axios"

export default{
    StoreUser(user){
        return Axios.post("SignUp",user);
    },
    VerifyEmail(email){
        return Axios.post("verify?email="+email);
    },
    forgotPassword(email){
        return Axios.post("ForgotPassword?email="+email);
    },
    changerPassword(info){
        return Axios.post("ChangerPassword",info)
    },
    async login(email,password){
         const store=AuthUser();
         const response=await Axios.post("login",{email,password});
         if(response.status==200){
         var admin=false;
         var ensignat=false;
         var etudiant=false;
         var chef=false;
         const roles=response.data.user['role'];
         for(let i=0;i<roles.length;i++){
            if(roles[i].roleName=="etudiant")
            {
                etudiant=true;
            }
            if(roles[i].roleName=="chefDepartment")
            {
                chef=true;
            }
            if(roles[i].roleName=="ensignant")
            {
                ensignat=true;
            }
            if(roles[i].roleName=="Admin")
            {
                admin=true;
            }
         }
            store.login(response.data.token,response.data.user,admin,false,etudiant,chef,ensignat);
        }else{
            store.logout();
        }
    }
}