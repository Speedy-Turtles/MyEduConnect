import { defineStore } from 'pinia'
import { ref,computed } from "vue"

export const CurentView = defineStore('current', ()=> {
    const view=ref(localStorage.getItem('view')??'stat');
    
    const getView=computed(()=>view.value);

    function setView(v){
        view.value=v;
        localStorage.setItem('view',v);
    };
    function suppView(){
        localStorage.removeItem('view');
    }

    return{view,getView,setView,suppView}
})