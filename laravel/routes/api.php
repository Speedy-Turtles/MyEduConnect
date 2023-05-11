<?php

use App\Http\Controllers\Admin\AdminController;
use App\Http\Controllers\Admin\VoteController;
use App\Http\Controllers\Auth\LoginController;
use App\Http\Controllers\Auth\RegisterController;
use App\Http\Controllers\Auth\RestPassword\ForgotPasswordController;
use App\Http\Controllers\Auth\VerifyEmailController;
use App\Http\Controllers\Document\ChefDepartementDocument;
use App\Http\Controllers\Document\DemandesController;
use App\Http\Controllers\GestionClasseController;
use App\Http\Controllers\GestionSpecialiteController;
use App\Http\Controllers\InfoUserController;
use App\Http\Controllers\Document\EmploiController;
use App\Http\Controllers\Document\DocumentController;
use App\Http\Controllers\EditProfil\EditController;
use App\Http\Controllers\nouveauteController;
use App\Http\Controllers\pdf\GenratePdfController;
use App\Http\Controllers\PdfController;
use App\Http\Controllers\proffesors\usersEtudiantContoroller;
use App\Http\Controllers\proffesors\UsersProffesorsController;
use App\Models\Notification;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});


Route::group(["prefix"=>"/auth"],function(){
    Route::post('/signup',[RegisterController::class,'Signup']);
    Route::post('/login',[LoginController::class,'Login']);
    Route::get('/verifyEmail/{email}',[VerifyEmailController::class,'VerifyEmail']);
    Route::get("/RenvoyerLink/{email}",[VerifyEmailController::class,'renvoyer']);
    Route::post("/forgotPassword/{email}",[ForgotPasswordController::class,'ForgotPassword']);
    Route::post("/ChangerPassword",[ForgotPasswordController::class,'ChangerPassword']);
    Route::get("/Existmail/{email}",[InfoUserController::class,'testExistEmail']);
    Route::get("/testExistToken/{code}",[InfoUserController::class,'testExistToken']);
});


Route::post('/active/{id}',[InfoUserController::class,"ActiveUser"]);

Route::group(["prefix"=>"/classe"],function(){
    Route::get("/classesBySpecialite/{id}",[GestionClasseController::class,"getClasseForSpecialte"]);
    Route::delete("/delete/{id}",[GestionClasseController::class,"DeleteClasse"]);
    Route::post("/AddClasse/",[GestionClasseController::class,"AddClasse"]);
    Route::get("/getClasse/{id}",[GestionClasseController::class,"getClasseByid"]);
    Route::put("/updateClasse/{id}",[GestionClasseController::class,"updateClasse"]);
});

Route::group(["prefix"=>"/specialte"],function(){
    Route::get("/Allspecialte",[GestionSpecialiteController::class,"getSpecialte"]);
    Route::post("/addSpecialite",[GestionSpecialiteController::class,"addSpecialite"]);
    Route::delete("/deleteSpecialite/{id}",[GestionSpecialiteController::class,"DeleteSpecialite"]);
    Route::get("/getSpecialiteById/{id}",[GestionSpecialiteController::class,"getElementById"]);
    Route::put("/updateSpecialite/{id}",[GestionSpecialiteController::class,"updateSpecialite"]);
});

Route::group(["prefix"=>"/nouveaute"],function(){
    Route::get("/Allnouveaute",[nouveauteController::class,"getAllNouveaute"]);
    Route::post("/addnouveaute",[nouveauteController::class,"addNouveaute"]);
    Route::delete("/deletenouveaute/{id}",[nouveauteController::class,"DeleteNouveaute"]);
    Route::get("/getnouveauteById/{id}",[nouveauteController::class,"getNouveauteById"]);
    Route::put("/updatenouveaute/{id}",[nouveauteController::class,"updateNouveaute"]);
});

Route::get("/show_etud",[InfoUserController::class,"show_etudiant"]);

Route::group(['prefix'=>"/admin"],function(){
    Route::get('/getstat',[AdminController::class,'GetStatistique']);
    Route::post('/StartVote',[VoteController::class,'StartVote']);
    Route::get('/test_Vote',[VoteController::class,'test_Vote']);
});
Route::get('GetUser/{id}',[InfoUserController::class,"GetUser"]);

Route::put("/edit/updateEmail",[EditController::class,"updateEmail"]);


Route::group(["prefix"=>"/pdf"],function(){
    Route::get('/generate/{id}', [GenratePdfController::class,"generatePDF"]);
});

Route::middleware("auth:sanctum")->group(function(){

    Route::get('getUserAuthentifie',[InfoUserController::class,"getUserAuthentifie"]);



    Route::group(["prefix"=>"/edit"],function(){
        Route::post("/uploadPhoto",[EditController::class,"editPhoto"]);
        Route::get("/SendChangedEmail/{email}",[EditController::class,"SendChangedEmail"]);
        Route::put("/EditInfoPersonnel",[EditController::class,"EditInfoPersonnel"]);
        Route::get("/CheckPassword/{password}",[EditController::class,"CheckPassword"]);
        Route::put("/ChangerPasswordActuel/{password}",[EditController::class,"ChangerPasswordActuel"]);
    });

        Route::get("/getNiveau",[InfoUserController::class,"GetNiveauUser"]);
        Route::post("/welcome",[InfoUserController::class,"updateWelcome"]);

    Route::group(['prefix'=>'/documents'],function(){
        Route::get('/',[DocumentController::class,'AllDocuments']);
        Route::post('/addDemande',[DocumentController::class,'addDemande']);
        Route::get('/getAlldemande',[DocumentController::class,'getAlldemande']);
        Route::post('/AccepterDocument',[DocumentController::class,'AccepterDocument']);
        Route::post('/initailiser_demande',[DocumentController::class,'initailiser_demande']);
    });

        Route::group(['prefix'=>"notif/"],function(){
            Route::get("getnotif",[InfoUserController::class,"getnotif"]);
            Route::post('ShowNotif',[InfoUserController::class,"ShowNotif"]);

        });

        Route::group(['prefix'=>"emploi/"],function(){
            Route::get("getemploi",[EmploiController::class,"getEmploi"]);

        });

        Route::group(['prefix'=>"vote/"],function(){
            Route::post('AddUserNominated_Session',[VoteController::class,"AddUserNominated_Session"]);
           /* Route::get('CheckUserNomanated/{id}',[VoteController::class,"CheckUserNomanated"]);
            Route::get('checkUserVoted/{id}',[VoteController::class,"checkUserVoted"]);*/
            Route::get('ListNominated',[VoteController::class,"ListNominated"]);
            Route::post("AddVote",[VoteController::class,"AddVote"]);
            Route::put("Annuler/{id}",[VoteController::class,"Annuler"]);
            Route::put("suspende/{id}",[VoteController::class,"suspende"]);
            Route::delete("deleteSession/{id}",[VoteController::class,"deleteSession"]);
            Route::get('NombreVoted/{id}',[VoteController::class,"NombreVoted"]);
            Route::get('GetSessionTerminer',[VoteController::class,"GetSessionTerminer"]);
            Route::get('getUserVoted',[VoteController::class,"getUserVoted"]);
            Route::get('getUserNomanitedByIdSession/{id}',[VoteController::class,"getUserNomanitedByIdSession"]);
            Route::get("getUserNominated",[VoteController::class,"getUserNominated"]);
            Route::get("getUserVoted",[VoteController::class,"getUserVoted"]);
            Route::get("CountNbrVote/{id}",[VoteController::class,"CountNbrVote"]);
            Route::put("CloseVote/{id}",[VoteController::class,"CloseVote"]);
        });

        Route::group(['prefix'=>'/documents'],function(){
            Route::get('/',[DocumentController::class,'AllDocuments']);
            Route::post('/addDemande',[DocumentController::class,'addDemande']);
            Route::get('/getAlldemande',[DocumentController::class,'getAlldemande']);
            Route::post('/AccepterDocument',[DocumentController::class,'AccepterDocument']);
            Route::post('/initailiser_demande',[DocumentController::class,'initailiser_demande']);
        });


    Route::group(['prefix'=>'/documents'],function(){
        Route::get('/',[DocumentController::class,'AllDocuments']);
        Route::post('/addDemande',[DocumentController::class,'addDemande']);
        Route::get('/getAlldemande',[DocumentController::class,'getAlldemande']);
        Route::post('/AccepterDocument',[DocumentController::class,'AccepterDocument']);
        Route::post('/initailiser_demande',[DocumentController::class,'initailiser_demande']);
    });
    Route::get("/notif",[DocumentController::class,'getNotifs']);
    Route::get("/notifNotSeen",[DocumentController::class,'getNotifsNotSeen']);
    Route::post("/editnotif",[DocumentController::class,'changerEtat']);
    Route::delete("/deleteAllNotif",[DocumentController::class,'clearNotif']);
    Route::delete("deleteNotification/{id}",[DocumentController::class,"deleteNotifById"]);
    // utiliser dans controller $request()->user()->id  grace a interceptors dans vue js
});
Route::group(['prefix'=>'/documents/demandes'],function(){
    Route::get('/pending',[DemandesController::class,'getAllDemandes']);
    Route::put('/AccepterDocument',[DemandesController::class,'AccepterDocument']);
    Route::put('/ReffuserDoccument',[DemandesController::class,'ReffuserDoccument']);
});
Route::group(['prefix'=>'/proffesors'],function(){
    Route::get('/accepted',[UsersProffesorsController::class,'acceptedProffesors']);
    Route::get('/pending',[UsersProffesorsController::class,'pendingRequests']);
});
Route::group(['prefix'=>'/proffesors/students'],function(){
    Route::get('/accepted',[usersEtudiantContoroller::class,'acceptedStudents']);
    Route::get('/pending',[usersEtudiantContoroller::class,'pendingRequests']);
});

