<template>
    <div>
        <div class="container">
            <div class="chat">
                <div class="chat-header text-center">
                       <div class="profil text-center">
                               <h2 class="text-center">My EduConnect</h2>
                               <v-btn v-if="affUser==true" @click="affUser=false" fab class="float-end mt-4 p-2">hide</v-btn>
                               <v-btn v-if="affUser==false" @click="affUser=true" fab class="float-end mt-4 p-2">show</v-btn>
                       </div>
                </div>
                <div class="chat-box">
                        <div  v-if="affUser==true " class="m-5 row scroll" >
                            <v-card v-for="user in Allusers"
                            :key="user.email"
                           class="col-lg-4 mb-2 mx-2"
                           max-width="344"
                           outlined
                         >
                         <v-list-item three-line>
                           <v-list-item-content>
                             <div class="text-overline mb-4">
                                 {{user.firstName}}  {{user.lastName}}
                             </div>
                         </v-list-item-content>
                           <v-list-item-avatar
                             tile
                             size="80"
                             color="grey"
                           >
                             <img :src="user.photo">
                         </v-list-item-avatar>
                         </v-list-item>
                           <v-card-actions>
                                <v-btn
                                    @click="chatPrive(user.id)"
                                    outlined
                                    rounded
                                    text
                                    color="green"
                                >
                                Send Message
                             </v-btn>
                           </v-card-actions>
                         </v-card>
                        </div>
                        <v-layout row wrap v-if="affUser==false ">
                            <v-flex> 
                                  <div class="scroll" >
                                    <div class="mb-5 mess" v-for="chat in Allchat" :key="chat">
                                        <v-layout wrap >
                                            <v-flex>
                                                <v-avatar>
                                                    <v-img :src="chat.userEnvoi['photo']"></v-img>
                                                </v-avatar>
                                                <span class="name ml-4">{{chat.userEnvoi['firstName']}} <span class="time"> {{(chat.created_at).substring(0,10)}}</span></span>
                                            </v-flex>
                                            </v-layout>
                                            <v-layout wrap>
                                                <v-flex class="mr-5">
                                                    <div class="message-div mt-4">
                                                        <span class="message">{{chat.message}}</span>
                                                    </div>
                                                </v-flex>
                                            </v-layout>
                                    </div>
                                </div>
                            </v-flex>
                        </v-layout>
                </div>
                <div v-if="affUser==false" class="chat-footer">
                    <v-text-field
                            name="message"
                            v-model="message"
                            :error-messages="message_error"
                            label="message"
                            class="text_field"
                             type="text"
                            placeholder="Type a message"
                ></v-text-field>
                    <div class="icons">
                       <v-btn @click="AddChat()" color="blue" style="color:#fff !important"  fab>
                           <v-icon>
                                mdi-send
                           </v-icon>
                       </v-btn> 
                    </div>    
               </div>
            </div>
        </div> 
        <v-dialog
        persistent
             v-model="dialog"
              width="600px"
      >
        <v-card>
          <v-card-text>
            <div class="containere" >
                <div class="chate">
                    <div class="chat-headere">
                           <div class="profile">
                               <div class="lefte">
                                   <!-- <img src="../../../../public/etudiant/images/chat/phone.png" class="cs"> -->
                                   <h2>{{UserCurrent.firstName}}</h2>
                                   <span>online</span>
                               </div>
                               <div class="righte">
                                   <!-- <img src="../../../../public/etudiant/images/chat/video.png" class="icon">
                                   <img src="../../../../public/etudiant/images/chat/phone.png"  class="icon">
                                   <img src="../../../../public/etudiant/images/chat/more.png"  class="icon"> -->
                               </div>
                               <v-spacer></v-spacer>
                               <v-btn
                                color="error"
                                 text
                                 @click="close()"
                               >
                                 <v-icon>
                                     mdi-close-circle
                                 </v-icon>
                               </v-btn>
                           </div>
                    </div>
                    <div class="chat-boxe" >
                       <div class="chat-areae">
                           <div class="chate"  v-for="chat in ChatPrivate" :key="chat">
                               <div class="chat-le" v-if="chat.id_envoi!=store.user['id']">
                                   <div class="mess">
                                       <p>
                                           {{ chat.message }}
                                       </p>
                                       <div class="check">
                                               <span>{{ chat.date }}</span>       
                                       </div>
                                   </div>
                               </div>
                               <div class="chat-re" v-else>
                                   <div class="sp"></div>
                                   <div class="mess mess-re">
                                       <p>
                                           {{ chat.message }}
                                       </p>
                                       <div class="check">
                                               <span>{{chat.date}}</span>
                                               <!-- <img src="../../../../public/etudiant/images/chat/check-2.png" > -->
                                       </div>
                                   </div>
                               </div>
                           </div>  
                       </div>
                    </div>
                    <form @submit.prevent="addMessage()" class="mt-2">
                       <div class="chat-footere">
                           <textarea placeholder="type a message" v-model="message_chat"></textarea>
                           <v-btn class="button ml-5 pa-5 white--text" color="#525fe1" type="submit">Send</v-btn> 
                       </div>
                   </form>
                </div>
            </div> 
          </v-card-text>
        </v-card>
      </v-dialog>
    </div>
</template>

<script>
import serviceChat from "@/service/chat/chat.js"
import {required} from "vuelidate/lib/validators";
import { AuthUser } from "@/store/AuthStore.js";
export default{
    validations:{
        message:{
            required
        },
        message_chat:{
            required
        }
    },
    setup(){
        const store=AuthUser();
        return{store}
    },
    created(){
        this.fetchData();
        this.fetchUser();
    },
    data(){
        return{
            Allchat:[],
            message:"",
            affUser:false,
            Allusers:[],
            ChatPrivate:[],
            dialog:false,
            message_chat:"",
            UserCurrent:[]
        }
    },
    methods:{
        close(){
            this.dialog=false;
            this.UserCurrent=[];
            this.ChatPrivate=[];
        },
        chatPrive(id){
            this.UserCurrent=[];
            serviceChat.AllChatPrivate(id).then((res)=>{
                for(let i=0;i<(res.data).length;i++){
                    this.ChatPrivate.push({id:res.data[i].id,id_envoi:res.data[i].userEnvoi['id'],id_recu:res.data[i].userRecu['id'],message:res.data[i].message,date:res.data[i].created_at.substring(0,10)});
                 }
            })
            serviceChat.getUserByID(id).then((res)=>{
                this.UserCurrent=res.data;
            })
            this.dialog=true;
        },
        fetchUser(){
            serviceChat.AllUserChat().then((res)=>{
                this.Allusers=res.data;
            })
        },
        fetchData(){
            serviceChat.Getchat().then((res)=>{
                this.Allchat=res.data;
            })
        },
        AddChat(){
            this.$v.message.$touch();
            if(this.$v.message.$invalid ){
                  return;
              }
            serviceChat.addMessage(this.message).then((res)=>{
               this.message="";
               this.fetchData();
            })
        },
        addMessage(){
             this.$v.message_chat.$touch();
              if(this.$v.message_chat.$invalid ){
                  return;
              }
              serviceChat.AddMessagePrivate(this.UserCurrent['id'],this.message_chat).then((res)=>{
                    this.chatPrive(this.UserCurrent['id']);
                    this.message_chat="";
              })
        }
    },
    computed:{
        message_error(){
              const error=[];
              if(!this.$v.message.$dirty) return error;
              !this.$v.message.required && error.push("Message required");
              return error;
          },
    }
}
</script>

<style scoped>


.chat{
    display: flex;
    flex-direction: column;
    height: 100vh;
}
.profil{
    width: 100%;
    background: #000;
    height: 100%;
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

.chat-box{
    flex: 1;
    background-color:whitesmoke;
    background-attachment: fixed;
    overflow-y: scroll;
    padding: 20px;
}
.chat-footer{
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 60px;
    position: relative;
    cursor: pointer;
}
.chat-footer .text_field{
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
    background:#fff;
    resize: none;
}

.chat-footer .envoyer{
    display: block;
    width: 35px;
    height: 35px;
    position: absolute;
    left: 10px;
    top: 12px;
}

.chat-boxe .mess{
    width: auto;
    background:#5094df;
    padding: 10px;
    color: #fff;
    border-radius: 10px;
    margin: 20px 0px;
    cursor: pointer;
}
.chat-box .mess{
    background:#5094df;
    padding: 10px;
    color: #fff;
    border-radius: 10px;
    margin: 20px 0px;
    cursor: pointer;
}


.chat-boxe .mess_recu{
    width: auto;
    background:#0c3c70;
    padding: 10px;
    color: #fff;
    border-radius: 10px;
    margin: 20px 0px;
    cursor: pointer;
}
.chat-box .mess p{
    word-break: break-all;
    font-size: 18px;
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

@media screen and (max-width:400px){
    .container{
        width: 100%;
    }
}


.containere{
    width: 500px;
    margin: auto;
}
.chate{
    display: flex;
    flex-direction: column;
    background: white;
}
.chat-headere{
    display: flex;
}
.profile{
    width: 100%;
    background: rgb(77, 96, 192);
    display: flex;
    align-items: center;
    height: 60px;
    padding: 0px 10px;
    position: relative;
}
.profile .cs{
    display: block;
    width: 40px;
    position: absolute;
    top: 12px;
    cursor: pointer;
    border-radius: 20px;
}
.profile h2{
    display: inline-block;
    line-height: 90px;
    vertical-align: bottom;
    color: #fff;
    margin-left: 50px;
    font-size: 20px;
    font-weight: 600;
}
.profile span{
    color: #ccc;
    position: absolute;
    top: 42px;
    left: 70px;
    font-size: 14px;
}
.profile .left{
    flex: 1;
}
.righte .icon{
    display: inline-block;
    width: 25px;
    margin-left: 8px;
}
.chat-areae{
    overflow-y: scroll;
    height:70vh;
}
.chat-boxe{
    flex: 1;
    color:#000 !important;
    /* background-image: url(../../../../public/etudiant/images/chat/bg.jpeg); */
    padding-left: 20px;
}
.chat-footere{
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 60px;
    position: relative;
    cursor: pointer;
}
.chat-footere textarea{
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
.chat-footere .mic{
    display: block;
    width: 45px;
    height: 45px;
    margin-top: -3px;
    margin-right: 18px;
}
.chat-footere .emo{
    display: block;
    width: 35px;
    height: 35px;
    position: absolute;
    left: 10px;
    top: 12px;
}
.chat-footere .icons{
    position: absolute;
    right: 100px;
    top: 10px;
}
.chat-footere .icons img{
    display: inline-block;
    width: 35px;
    height: 35px;
}
.chat-re{
    display: flex;
}
.chat-re .sp{
    flex: 1;
}
.chat-le{
    display: flex;
}
.chat-le .sp{
    flex: 1;
}
.chat-boxe .mess{
    max-width: 300px;
    background-color: #f7fcf6;
    padding: 10px;
    color:#000 !important;
    border-radius: 10px;
    margin: 20px 0px;
    cursor: pointer;
}
.chat-boxe .mess p{
    word-break: break-all;
    font-size: 18px;
    color:#000 !important;
}
.chat-boxe .mess-r{
    background: #e2ffc7;
}
.chat-boxe .emoji{
    width: 20px; 
}
.chat-boxe .check{
    float: right;
}
.chat-boxe .check img{
    width: 20px;
}
.chat-boxe .check span{
    color: #888;
    font-size: 12px;
    font-weight: 700px;
}






</style>

