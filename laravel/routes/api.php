<?php
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

Route::middleware("auth:sanctum")->group(function(){

    Route::get("/getNiveau",[InfoUserController::class,"GetNiveauUser"]);

    Route::group(['prefix'=>'/documents'],function(){
        Route::get('/',[DocumentController::class,'AllDocuments']);
        Route::post('/addDemande',[DocumentController::class,'addDemande']);
    });

    // utiliser dans controller $request()->user()->id  grace a interceptors dans vue js
});
