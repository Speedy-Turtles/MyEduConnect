<template>
    <div id="tab1">
        <div class="navbar">
         <v-toolbar   class="toolbar">
            <a type="hidden" href=""></a>
                <!-- --------------------titre-------------------------- -->
                <v-toolbar-title class="mr-5">
                    <img src="../../../public/images/logo-no-background.svg" alt="" width="110px" srcset="">
                </v-toolbar-title>
                <!-- --------------------titre/-------------------------- -->
                <v-spacer></v-spacer>
                <v-container>
                    <div class="btns float-end">
                        <v-btn outlined rounded color="#ffcf59" class="me-5" :to="links[0].route">
                        {{ links[0].title }} 
                        </v-btn>

                        <v-btn elevation="0" rounded color="#ffcf59" class="me-5 p-y5" dark :to="links[1].route">
                            {{ links[1].title }}  
                        </v-btn> 
                    </div>
                </v-container>
        </v-toolbar> 
    </div>
    </div>
</template>

<script>



export default {
    data(){
        return{
            links:[
            {title:'SIGN IN',route:'signin'},
            {title:'SIGN UP',route:'signup'}
        ]
        }
    },  
    methods:{
    },
    components: {
    },
}
</script>
<style scoped>
.toolbar{
    position: fixed;
    z-index: 999;
    width: 100%;
}
</style>