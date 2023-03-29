import { defineStore } from 'pinia'
import { ref,computed } from "vue"
export const AuthUser = defineStore('auth', ()=> {

    const token=ref(localStorage.getItem('token')??null);
    const user=ref(JSON.parse(localStorage.getItem('user')??null));
    const isAuth=ref(localStorage.getItem('token')&&localStorage.getItem('user'));
    const IsAdmin=ref(localStorage.getItem('isAdmin')??false);
    const IsTechnicien=ref(localStorage.getItem('IsTechnicien')??false);
    const Isetudiant=ref(localStorage.getItem('Isetudiant')??false);
    const Ischef=ref(localStorage.getItem('Ischef')??false);

    const getUser=computed(()=>user.value);
    const gettoken=computed(()=>token.value);
    const getisauth=computed(()=>isAuth.value);
    const getIsadmin=computed(()=>IsAdmin.value);
    const getIsTechnicien=computed(()=>IsTechnicien.value);
    const getIsetudiant=computed(()=>Isetudiant.value);
    const getIschef=computed(()=>Ischef.value);


    function login(t,u,idadm,istech,isetud,ische){
        user.value=u;
        token.value=t;
        isAuth.value=true;
        IsAdmin.value=idadm;
        IsTechnicien.value=istech;
        Ischef.value=ische;
        Isetudiant.value=isetud;
        localStorage.setItem('token',t);
        localStorage.setItem('isAdmin',idadm);
        localStorage.setItem('IsTechnicien',istech);
        localStorage.setItem('Ischef',ische);
        localStorage.setItem('Isetudiant',isetud);
        localStorage.setItem('user',JSON.stringify(u));
    }

    function logout(){
        user.value=null;
        token.value=null;
        isAuth.value=false;
        IsAdmin.value=false;
        IsTechnicien.value=false;
        Ischef.value=false;
        Isetudiant.value=false;
        localStorage.removeItem('token');
        localStorage.removeItem('isAdmin');
        localStorage.removeItem('IsTechnicien');
        localStorage.removeItem('Ischef');
        localStorage.removeItem('Isetudiant');
        localStorage.removeItem('user');
    }

    return {
        token,user,isAuth,IsAdmin,IsTechnicien,Isetudiant,Ischef,
        getUser,gettoken,getisauth,getIsadmin,getIsTechnicien,getIsetudiant,getIschef,
        login,logout
    }
})