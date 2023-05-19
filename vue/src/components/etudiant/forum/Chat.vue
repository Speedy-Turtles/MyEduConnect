<template>
    <div class="">
            <div class="loading text-center py-5 mt-5" v-if="loding_page==false">
                <v-progress-circular
                indeterminate
                color="primary"
                ></v-progress-circular>
            </div>
        <div class="container" v-else>
         <div class="chat">
             <div class="chat-header">
                    <div class="profil">
                        <div class="left">
                            <img src="../../../../public/etudiant/images/chat/phone.png" class="cs">
                            <h2>{{user[0].FirstName}}</h2>
                            <span>online</span>
                        </div>
                        <div class="right">
                            <img src="../../../../public/etudiant/images/chat/video.png" class="icon">
                            <img src="../../../../public/etudiant/images/chat/phone.png"  class="icon">
                            <img src="../../../../public/etudiant/images/chat/more.png"  class="icon">
                        </div>
                    </div>
             </div>
             <div class="chat-box" >

                <div class="chat-area">
                    <div class="chat"  v-for="chat in chats" :key="chat.id">
                        <div class="chat-l" v-if="chat.id_envoi!=store.user['id']">
                            <div class="mess">
                                <p>
                                    {{ chat.message }}
                                </p>
                                <div class="check">
                                        <span>{{ chat.date }}</span>       
                                </div>
                            </div>
                        </div>

                        <div class="chat-r" v-else>
                            <div class="sp"></div>
                            <div class="mess mess-r">
                                <p>
                                    {{ chat.message }}
                                </p>
                                <div class="check">
                                        <span>{{chat.date}}</span>
                                        <img src="../../../../public/etudiant/images/chat/check-2.png" >
                                </div>
                            </div>
                        </div>
                    </div>  
                </div>
                    
             </div>

             <form @submit.prevent="addMessage()" class="mt-2">
                <div class="chat-footer">
                    <textarea placeholder="type a message" v-model="message"></textarea>
                    <v-btn class="button ml-5 pa-5 white--text" color="#525fe1" type="submit">Send</v-btn> 
                </div>
            </form>
         </div>
     </div> 
    </div>
</template>
<script>
import { AuthUser } from '@/store/Store';
import forumService from '@/service/forum/forumService';
export default {
    mounted(){
        window.Echo.channel('chatRealTime').listen('chatlive',(e)=>{
               this.getChatById();
            }) 
    },
    setup(){
            const store=AuthUser();
            return{store}
        },
    created(){
        this.id = this.$route.params.id;
        console.log(this.id);
        this.getChatById();
        this.getUserById();
        console.log(this.store.user['id'])
    },

    data(){
        return{
            id:-1,
            chats:[],
            user:[],
            message:"",
            loding_page:false,
        }
    },
    methods:{
        getChatById(){
            forumService.getChatById(this.id).then((res=>{
            
                for(let i=0;i<(res.data.data).length;i++){
                     this.chats.push({id:res.data.data[i].id,id_envoi:res.data.data[i].id_envoi,id_recu:res.data.data[i].id_recu,message:res.data.data[i].message,date:res.data.data[i].created_at.substring(0,10)});
                 }
                 this.loding_page=true;
            })).catch((err=>{
                console.log(err);
            }))
        },
        getUserById(){
            forumService.getUseById(this.id).then((res=>{
                this.user=res.data.data
                console.log(this.user[0].FirstName);
            })).catch((err=>{
                console.log(err);
            }))
        },
        addMessage(){
            forumService.addMessage(this.id,{
                "message":this.message
            }).then((res=>{
                this.message="";
                console.log(res.data.data);
            })).catch((err=>{
                console.log(err);
            }))
        }
    }
}
</script>
<style scoped>
.loading{
    height: 100vh;
}




*{
    padding: 0;
    margin: 0;
    box-sizing: border-box;
    font-family: 'Times New Roman', Times, serif;
}
.container{
    width: 500px;
    margin: auto;
}
.chat{
    display: flex;
    flex-direction: column;
    background: white;
}
.chat-header{
    display: flex;
}
.profil{
    width: 100%;
    background: rgb(77, 96, 192);
    display: flex;
    align-items: center;
    height: 60px;
    padding: 0px 10px;
    position: relative;
}
.profil .cs{
    display: block;
    width: 40px;
    position: absolute;
    top: 12px;
    cursor: pointer;
    border-radius: 20px;
}
.profil h2{
    display: inline-block;
    line-height: 90px;
    vertical-align: bottom;
    color: #fff;
    margin-left: 50px;
    font-size: 20px;
    font-weight: 600;
}
.profil span{
    color: #ccc;
    position: absolute;
    top: 42px;
    left: 70px;
    font-size: 14px;
}
.profil .left{
    flex: 1;
}
.right .icon{
    display: inline-block;
    width: 25px;
    margin-left: 8px;
}
.chat-area{
    overflow-y: scroll;
    height:70vh;
}
.chat-box{
    flex: 1;
    padding-left: 20px;
}
.chat-footer{
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 60px;
    position: relative;
    cursor: pointer;
}
.chat-footer textarea{
    display: block;
    flex:1;
    width: 100%;
    border-radius: 60px;
    margin: 5px;
    height: 50px;
    outline: none;
    font-size: 19px;
    padding: 10px;
    padding-left: 40px;
    padding-right:90px ;
    border: 2px solid #ccc;
    color: #555;
    resize: none;
}
.chat-footer .mic{
    display: block;
    width: 45px;
    height: 45px;
    margin-top: -3px;
    margin-right: 18px;
}
.chat-footer .emo{
    display: block;
    width: 35px;
    height: 35px;
    position: absolute;
    left: 10px;
    top: 12px;
}
.chat-footer .icons{
    position: absolute;
    right: 100px;
    top: 10px;
}
.chat-footer .icons img{
    display: inline-block;
    width: 35px;
    height: 35px;
}
.chat-r{
    display: flex;
}
.chat-r .sp{
    flex: 1;
}
.chat-l{
    display: flex;
}
.chat-l .sp{
    flex: 1;
}
.chat-box .mess{
    max-width: 300px;
    background-color: #f7fcf6;
    padding: 10px;
    border-radius: 10px;
    margin: 20px 0px;
    cursor: pointer;
}
.chat-box .mess p{
    word-break: break-all;
    font-size: 18px;
}
.chat-box .mess-r{
    background: #e2ffc7;
}
.chat-box .emoji{
    width: 20px; 
}
.chat-box .check{
    float: right;
}
.chat-box .check img{
    width: 20px;
}
.chat-box .check span{
    color: #888;
    font-size: 12px;
    font-weight: 700px;
}
*::-webkit-scrollbar{
    width: 5px;
}
*::-webkit-scrollbar-track{
    background-color: #f1f1f1;
}
*::-webkit-scrollbar-thumb{
    background-color: #aaa;
}
*::-webkit-scrollbar-thumb:hover{
    background-color: #555;
}
.chat-box .img_chat{
    width: 280px;
}
@media screen and (max-width:400px){
    .container{
        width: 376px;
    }
}
</style>