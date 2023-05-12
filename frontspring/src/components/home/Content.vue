<template>
    <div class="">
        <section class="contenu">
            <div class="home">
                <div class="content">
                    <v-container>
                        <v-layout column>
                            <v-flex><h3 class="hidden-md-and-down">the best courses you will find here</h3></v-flex>
                            <v-flex><p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Assumenda, corrupti. Atque provident veniam eligendi doloribus placeat reiciendis!</p></v-flex>
                            <v-flex><v-btn color="#12c2b9" class="white--text pa-7 ">Learn More</v-btn></v-flex>
                        </v-layout>
                    </v-container>
                </div>
            </div>
            <div class="line1">
            </div>
            <div class="sec2">
            <v-container  class="mt-5 py-5">
                    <v-container fluid class="py-4">
                        <v-layout flex wrap class="py-4  align-center" >
                            <v-flex xl8 lg8 md12 sm12 xs12 data-aos="fade-right">
                                <img id="im2" src="../../assets/about-1.jpg" width="600px" height="500px" class="px-5">
                            </v-flex>
                            <v-flex xl4 lg4 md12 sm12 xs12 data-aos="fade-left align-center">
                                <p class="p1 mt-10 mb-10 hidden-sm-and-down">{{ p4 }}</p>
                                <p class="p2 hidden-sm-and-down ">{{ p3 }}</p>
                            </v-flex>
                            
                        </v-layout>
                    </v-container>
            </v-container>
            </div>
            <div class="line1">
            </div>

            <div class="service">
                <div class="subject">
                    <h3 class="">OUR POPULAR SUBJECTS</h3>
                </div>
                <div class="sec3"> 
                    <v-container fluid>       
                        <v-layout flex wrap>
                            <v-flex xl3 lg3 md6 sm6 xs12 v-for="card in cards" :key="card.title" class="px-5 py-5" data-aos="fade-down">
                                <div class="card py-5">
                                    <v-avatar size="100">
                                        <v-img :src="card.image" class="pa-5"></v-img>
                                    </v-avatar>
                                    <h2 class="align-center mt-5" id="h2">{{ card.title }}</h2>
                                    <p id="p" class="">{{ card.p }}</p>
                                </div>
                            </v-flex>
                        </v-layout>
                        
                    </v-container>
                </div>
            </div>

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
                                                    <v-img :src="equipe.image" width="600px" height="400px" id="img"></v-img>
                                                </div>   
                                                <p class="py-5 m-5">{{equipe.name}}</p>
                                            </div>
                                        </v-flex>       
                            </v-layout>
                        </div>
                    </v-container>
                </div>
                
                <!-- ------------------ /equipe ------------------------------------>
        
        </section>
    </div>
</template>
<script>
export default {
    data(){
        return{
            cards:[
            {title:'Document',p:'A document official is an official document that has been created, issued, or certified by the university.',image:require('../../assets/document2.png')},
            {title:'Forum',p:'A forum is an online discussion  where users can engage in conversations, share ideas and opinions, and ask and answer questions on various topics.',image:require('../../assets/forum2.png')},
            {title:'Club',p:'A Club is an organization that connects graduates of a particular university or college, provides them with opportunities for networking and socializing.',image:require('../../assets/club.png')},
            {title:'Help',p:'The help desk typically offers a single point of contact for users to seek assistance from trained technicians who can diagnose and troubleshoot issues and provide solutions.',image:require('../../assets/help.png')},
            
        ],
        p3:'Online education has revolutionized the way we learn by providing students with the flexibility and convenience to access educational materials remotely. This mode of learning offers a wide range of programs and courses to choose from, allowing students to tailor their education to fit their needs and interests. Online education is particularly beneficial for those who have other commitments such as work or family obligations, as it allows them to learn on their own schedule. While it does present some challenges, such as a lack of face-to-face interaction, advances in technology are addressing these issues. Overall, online education has become a popular and effective means of learning in today s digital age.',
        p4:'Online Education',
        equipes:[
            {name:'NAAMEN MALEK',image:require('../../assets/malek.jpeg')},
            {name:'MEJRI TALEL',image:require('../../assets/talel.jpg')},
            {name:'HKIMI MOHAMED AMINE ',image:require('../../assets/hkimi.jpg')},
            {name:'AHMED GHASSEN',image:require('../../assets/ghassen.jpg')},
        ]
        }
    }
}
</script>
<style scoped>
.content{
    min-height: 100vh;
	display: flex;
	align-items: center;
	background: url("../../assets/home.jpg") no-repeat;
	background-size: cover;
	background-position: center
}
.home .content h3 {
    text-transform: capitalize;
    text-align: justify;
	font-size: 2rem;
	color: white;
	line-height: 1.2;
    font-weight: bolder;
}
.home .content p {
    text-transform: capitalize;
    text-align: justify;
	font-size: 1.5rem;
	line-height: 2;
	color: white;
	padding: 1rem 0;
}

.heading {
    margin-top: 50px;
	position: relative;
	color: #12c2b9;
	font-size: 3rem;
	font-weight: 700;
	text-transform: uppercase;
	letter-spacing: .4rem;
	text-align: center;
	margin-bottom: 3rem;
    cursor: pointer;
}

.heading::after {
	display: block;
	width: 10rem;
	margin-inline: auto;
	margin-top: -1rem;
}

#about-image1{
    position: relative;
    width: 700px;
    height: 600px;

}
#about-image2{
    position: absolute;
    width: 500px;
    height: 400px;
    top: 55%;
    left: 40%;
}
.card{
    border:2px solid#12c2b9;
    padding: 20px;
    height: 45vh;
    background-color: #dbfffe;
    color: #444;
    display:flex;
    flex-direction: column;
    align-items: center;
}

.card:hover{
    background-color: #12c2b9;
    color: white;
    
}
.service{
    /* margin-top: 400px; */
}
.p1{
    font-family: Verdana, Geneva, sans-serif;
    font-size: 25px;
    color: #12c2b9;
    font-weight: 400;
    text-decoration: none solid rgb(68, 68, 68);
    font-style: normal;
    font-variant: small-caps;

}
.line1{
    margin-top: 100px;
    background-color: #12c2b9;
    height: 40px;
    clip-path: polygon(0 41%, 98% 69%, 100% 75%, 0 71%);
    width: 100%;
}
.p2{
    font-family: Verdana, Geneva, sans-serif;
    font-size: 17px;
    text-align: justify;
    color: #000000;
    font-weight: 400;
    text-decoration: none solid rgb(68, 68, 68);
    font-style: normal;
    font-variant: small-caps;
}
.sec3{
    margin-top: 100px;
    margin-bottom: 200px;
}

.subject{
    text-align: center;
    margin-top: 100px;
    left:50%;
    
}
.subject>h3{
    font-family: 'DM Sans', sans-serif;
    color: #12c2b9;
    font-size: 30px;
    display: inline-block;
}
.equipe{
    margin-bottom: 100px;
    cursor: pointer;
}
#img{
    border-radius: 15px;
    -webkit-box-shadow: 1px 0px 11px -2px #12c2b9; 
    box-shadow: 1px 0px 11px -2px #12c2b9;
}


</style>