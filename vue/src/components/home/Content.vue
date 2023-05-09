<template>
    <div>
        <v-container  class=" fluid mt-5 py-5 sec1">
            <!-- ------------------section 1 ------------------------------------>
            <div class="sec1">
                <v-container class="py-4 ">
                    <v-layout flex wrap class="py-4" >
                        <v-flex xl6 lg6 md6 sm12 xs12 data-aos="fade-right">
                            <div class="title">
                                <span class="title-word title-word-1">My</span>
                                <span class="title-word title-word-2">Edu</span>
                                <span class="title-word title-word-3">Connect</span>
                            </div>
                            <p class="p1">{{ p1 }}</p>
                            <p class="p2 hidden-sm-and-down">{{ p2 }}</p>
                        </v-flex>
                        <v-flex xl6 lg6 md6 sm12 xs12 data-aos="fade-left">
                            <img id="im1" src="../../../public/images/person-studying.png" width="500px">
                        </v-flex>
                    </v-layout>
                </v-container>
            </div>
               
            <!-- ------------------ /section 1 ------------------------------------>
        </v-container>
 
        <!-- ------------------section 2 ------------------------------------>
        <div class="sec2">
            <v-container  class=" fluid mt-5 py-5">
                    <v-container class="py-4">
                        <v-layout flex wrap class="py-4" >
                            <v-flex xl6 lg6 md6 sm12 xs12 data-aos="fade-right">
                                <img id="im2" src="../../../public/images/person-managing-it-settings.png" width="500px" class="px-5">
                            </v-flex>
                            <v-flex xl6 lg6 md6 sm12 xs12 data-aos="fade-left">
                                <p class="p1">{{ p4 }}</p>
                                <p class="p2 hidden-sm-and-down">{{ p3 }}</p>
                            </v-flex>
                            
                        </v-layout>
                    </v-container>
            </v-container>
        </div>
        <div class="line1">
        </div>
        <!-- ------------------ /section 2 ------------------------------------>


        <!-- ------------------section 3 ------------------------------------>
        <div class="subject">
            <h3 class="">OUR POPULAR SUBJECTS</h3>
        </div>
        
        <div class="sec3">
            
            <v-container fluid>
                    
                    <v-layout flex wrap>
                        <v-flex xl3 lg3 md6 sm6 xs12 v-for="card in cards" :key="card.title" class="px-5 py-5" data-aos="fade-down">
                            <div class="card">
                                <v-avatar size="100">
                                    <v-img :src="card.image" class="pa-5"></v-img>
                                </v-avatar>
                                <h2 class="align-center" id="h2">{{ card.title }}</h2>
                                <p id="p" class="">{{ card.p }}</p>
                            </div>
                        </v-flex>
                    </v-layout>
               
            </v-container>
        </div>
        
        <!-- ------------------ /section 3 ------------------------------------>


        <!-- ------------------ equipe ------------------------------------>
        <div class="subject">
            <h3 class="">OUR BEAUTIFUL TEAM</h3>
        </div>
        <div class="equipe">
            <v-container fluid>
                <div class="card2 pa-4 text-center rounded-0">
                    <v-layout flex wrap class="mt-5 py-5">
                                <v-flex xl3 lg3 md6 sm6 xs12 class="mt-5 py-5 px-5" v-for="equipe in equipes" :key="equipe.name" data-aos="fade-down">
                                    <div class="card-body">
                                        <div class="img">
                                            <v-img :src="equipe.image" width="600px" height="400px"></v-img>
                                        </div>   
                                        <p class="py-5 m-5">{{equipe.name}}</p>
                                    </div>
                                </v-flex>
                           
                    </v-layout>
                </div>
            </v-container>
        </div>
        
        <!-- ------------------ /equipe ------------------------------------>
    </div>
    
</template>

<script>

export default {
    props:{
    },
    created(){
    },
    data: () => ({
        p1:' is a website that caters specifically to college students .',
        p2:'Myeduconnect is an innovative education website that offers a range of features to support students in their academic journey. One of the main benefits of this platform is its provision of professional documents, such as academic articles, research papers, and study guides, which can be accessed by students to enhance their learning experience. Additionally, Myeduconnect provides a forum for students to engage in discussions, share ideas, and collaborate with peers on academic topics. Moreover, the website has numerous clubs that cater to diverse interests, enabling students to explore their passions beyond the classroom. ',
        p3:'Online education has revolutionized the way we learn by providing students with the flexibility and convenience to access educational materials remotely. This mode of learning offers a wide range of programs and courses to choose from, allowing students to tailor their education to fit their needs and interests. Online education is particularly beneficial for those who have other commitments such as work or family obligations, as it allows them to learn on their own schedule. While it does present some challenges, such as a lack of face-to-face interaction, advances in technology are addressing these issues. Overall, online education has become a popular and effective means of learning in today s digital age.',
        p4:'Online Education',
        cards:[
            {title:'Document',p:'A document official is an official document that has been created, issued, or certified by the university.',image:require('../../../public/etudiant/images/document2.png')},
            {title:'Forum',p:'A forum is an online discussion  where users can engage in conversations, share ideas and opinions, and ask and answer questions on various topics.',image:require('../../../public/etudiant/images/forum2.png')},
            {title:'Club',p:'A Club is an organization that connects graduates of a particular university or college, provides them with opportunities for networking and socializing.',image:require('../../../public/etudiant/images/club.png')},
            {title:'Help',p:'The help desk typically offers a single point of contact for users to seek assistance from trained technicians who can diagnose and troubleshoot issues and provide solutions.',image:require('../../../public/etudiant/images/help.png')},
            
        ],

        equipes:[
            {name:'NAAMEN MALEK',image:require('../../assets/team/image2.jpeg')},
            {name:'MEJRI TALEL',image:require('../../assets/team/pro.jpg')},
            {name:'HKIMI MOHAMED AMINE ',image:require('../../assets/team/DSC_6972 (1).jpg')},
            {name:'AHMED GHASSEN',image:require('../../assets/team/ghassen.jpg')},
        ]
    }),
    components: {
    },
}
</script>
<style scoped>
.p1{
    font-family: Verdana, Geneva, sans-serif;
font-size: 30px;
color: #000000;
font-weight: 400;
text-decoration: none solid rgb(68, 68, 68);
font-style: normal;
font-variant: small-caps;

}
.p2{
    font-family: Verdana, Geneva, sans-serif;
    font-size: 15px;
    text-align: justify;
    color: #000000;
    font-weight: 400;
    text-decoration: none solid rgb(68, 68, 68);
    font-style: normal;
    font-variant: small-caps;
}

.title-word {
  animation: color-animation 4s linear infinite;
}

.title-word-1 {
  --color-1: #f66641;
  --color-2: #525fe1;
  --color-3: #ff60aa;
}

.title-word-2 {
  --color-1: #ffcf59;
  --color-2: #5094df;
  --color-3: #a6cd49;
}

.title-word-3 {
  --color-1: #ACCFCB;
  --color-2: #E4A9A8;
  --color-3: #ACCFCB;
}

@keyframes color-animation {
  0%    {color: var(--color-1)}
  32%   {color: var(--color-1)}
  33%   {color: var(--color-2)}
  65%   {color: var(--color-2)}
  66%   {color: var(--color-3)}
  99%   {color: var(--color-3)}
  100%  {color: var(--color-1)}
}
#im1{
    clip-path: polygon(0 5%, 100% 8%, 100% 94%, 0 96%);
}

.line1{
    background-color: #525fe1;
    height: 40px;
    clip-path: polygon(0 41%, 98% 69%, 100% 75%, 0 71%);
    width: 100%;
}
#im2{
    clip-path: polygon(0 7%, 100% 0, 100% 100%, 0 94%);
}

#h2{
    font-family: "Times New Roman", Times, serif;
    font-size: 20px;
    margin-top: 50px;
    letter-spacing: 5.4px;
    word-spacing: -5px;
    font-weight: 700;
    text-decoration: none;
    font-style: normal;
    font-variant: small-caps;
    text-transform: none;
    text-align: center;
    }
#p{
    font-size: 10px;
    margin-top: 20px;
    letter-spacing:2px;
    text-decoration: none;
    font-style: normal;
    text-transform: none;
    text-align:justify;
}
.sec2,.sec3{
    margin-top: 100px;
    margin-bottom: 200px;
}
.sec1{
    margin-top: 100px;
}
.equipe{
    margin-bottom: 100px;
}
.card{
    border:2px solid#1976D2;
    padding: 20px;
    height: 45vh;
    background-color: #81D4FA;
    color: #444;
    display:flex;
    flex-direction: column;
    align-items: center;
}
/* .card>h2,p{
    color: white;
} */
.card:hover{
    background-color: #1976D2;
    color: white;
    
}
.subject{
    text-align: center;
    margin-top: 100px;
    left:50%;
    
}
.subject>h3{
    font-family: 'DM Sans', sans-serif;
    color: #1976D2;
    font-size: 30px;
    display: inline-block;
}
.card-body{
    border-radius: 5px;
    -webkit-box-shadow: -6px 5px 20px -10px #103a64; 
            box-shadow: -6px 5px 20px -10px #103a64;
}
</style>