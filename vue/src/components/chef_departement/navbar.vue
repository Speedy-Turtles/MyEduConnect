<template>
    <div>
    <v-toolbar elevation="4" rounded fixed>
        <img src="../../assets/logo-no-background.svg" width="100px" alt="">
        <v-spacer></v-spacer>
        <v-menu offset-y>
            <template v-slot:activator="{ on, attrs }">
                <v-btn icon v-bind="attrs" v-on="on" style="margin-right: 2%;">
                    <v-badge color="red" content="6">
                        <v-icon>mdi-bell</v-icon>
                    </v-badge>
                </v-btn>
            </template>
            <v-list>
                <v-list-item-title>
                    <v-list-item class="notification">notifications 1</v-list-item>
                    <v-list-item class="notification">notifications 2</v-list-item>
                    <v-list-item class="notification">notifications 3</v-list-item>
                </v-list-item-title>
            </v-list>
        </v-menu>
        <v-btn icon >
            <v-avatar>
                <v-img src="../../assets/DSC_6972 (1).jpg" alt="John"></v-img>
            </v-avatar>
            <!-- <v-icon right style="margin-left: 4%;">mdi-arrow-down-drop-circle</v-icon> -->
        </v-btn>
    </v-toolbar>
</div>
</template>
<script>
export default {
    name: 'navbar',
    data() {
        return {

        }
    }
}
</script>
<style scoped>
.v-toolbar{
    position: fixed;
    width: 100%;
}
.notification{
    cursor: pointer;
}
</style>