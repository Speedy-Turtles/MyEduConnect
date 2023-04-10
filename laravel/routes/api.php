<?php

use App\Http\Controllers\Admin\AdminController;
use App\Http\Controllers\Admin\VoteController;
use App\Http\Controllers\Auth\LoginController;
use App\Http\Controllers\Auth\RegisterController;
use App\Http\Controllers\Auth\RestPassword\ForgotPasswordController;
use App\Http\Controllers\Auth\VerifyEmailController;
use App\Http\Controllers\GestionClasseController;
use App\Http\Controllers\GestionSpecialiteController;
use App\Http\Controllers\InfoUserController;
use App\Http\Controllers\Document\DocumentController;
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
 });

 Route::group(["prefix"=>"/specialte"],function(){
    Route::get("/Allspecialte",[GestionSpecialiteController::class,"getSpecialte"]);
});

Route::get("/show_etud",[InfoUserController::class,"show_etudiant"]);

Route::group(['prefix'=>"/admin"],function(){
    Route::get('/getstat',[AdminController::class,'GetStatistique']);
    Route::post('/StartVote',[VoteController::class,'StartVote']);
    Route::get('/test_Vote',[VoteController::class,'test_Vote']);
});

Route::middleware("auth:sanctum")->group(function(){

        Route::get("/getNiveau",[InfoUserController::class,"GetNiveauUser"]);
        Route::get("notif/getnotif",[InfoUserController::class,"getnotif"]);
        Route::post('notif/ShowNotif',[InfoUserController::class,"ShowNotif"]);
        Route::post('vote/AddUserNominated_Session',[VoteController::class,"AddUserNominated_Session"]);
        Route::get('vote/CheckUserNomanated',[VoteController::class,"CheckUserNomanated"]);
        Route::get('vote/checkUserVoted/{id}',[VoteController::class,"checkUserVoted"]);
        Route::get('vote/ListNominated',[VoteController::class,"ListNominated"]);
        Route::post("vote/AddVote",[VoteController::class,"AddVote"]);
        Route::delete("vote/Annuler/{id}",[VoteController::class,"Annuler"]);
        Route::post("vote/suspende/{id}",[VoteController::class,"suspende"]);
        Route::get('GetUser/{id}',[InfoUserController::class,"GetUser"]);
        Route::get('vote/NombreVoted/{id}',[VoteController::class,"NombreVoted"]);
        Route::group(['prefix'=>'/documents'],function(){
        Route::get('/',[DocumentController::class,'AllDocuments']);
        Route::post('/addDemande',[DocumentController::class,'addDemande']);
        Route::get('/getAlldemande',[DocumentController::class,'getAlldemande']);
        Route::post('/AccepterDocument',[DocumentController::class,'AccepterDocument']);
        Route::post('/initailiser_demande',[DocumentController::class,'initailiser_demande']);

    });

    // utiliser dans controller $request()->user()->id  grace a interceptors dans vue js
});
