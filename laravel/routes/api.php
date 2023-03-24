<?php

use App\Http\Controllers\Auth\RegisterController;
use App\Http\Controllers\Auth\VerifyEmailController;
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
    Route::get('/verifyEmail/{email}',[VerifyEmailController::class,'VerifyEmail']);
    Route::get("/RenvoyerLink/{email}",[VerifyEmailController::class,'renvoyer']);
});

