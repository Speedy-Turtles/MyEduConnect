<?php

namespace App\Http\Controllers\chefDepartement;

use App\Http\Controllers\Controller;
use App\Models\Classe;
use App\Models\Demande;
use App\Models\nouveaute;
use App\Models\Specialite;
use Illuminate\Http\Request;
use App\Models\User;

class HomeController extends Controller
{
    public function getStats(){
    $nbr_ensignants=User::whereHas('roles',function($query){
        $query->where("Role_name","ensignant")->where('status',1);
    })->count();
    $nbr_etudiant=User::whereHas('roles',function($query){
        $query->where("Role_name","etudiant")->where('status',1);
    })->count();
    $nbr_classes=Classe::count();
    $nbr_specialite=Specialite::count();
    $nbr_neauveaute=nouveaute::count();
    $nb_demande_accepte=Demande::where("etat",1)->count();
    $nb_demande_refuse=Demande::where("etat",-1)->count();
    $nb_demande_encours=Demande::where("etat",0)->count();
    $etudiant_niveau1=Specialite::where('niveau',1)->with('classes.users')->count();
    $etudiant_niveau2=Specialite::where('niveau',2)->with('classes.users')->count();
    $etudiant_niveau3=Specialite::where('niveau',3)->with('classes.users')->count();
    $stats=[
        'nbr_ensignants'=>$nbr_ensignants,
        'nbr_etudiant'=>$nbr_etudiant,
        'nb_demande_accepte'=>$nb_demande_accepte,
        'nb_demande_refuse'=>$nb_demande_refuse,
        'nb_demande_encours'=>$nb_demande_encours,
        'etudiant_niveau1'=>$etudiant_niveau1,
        'etudiant_niveau2'=>$etudiant_niveau2,
        'etudiant_niveau3'=>$etudiant_niveau3,
        'nbr_classes'=>$nbr_classes,
        'nbr_specialite'=>$nbr_specialite,
        'nbr_neauveaute'=>$nbr_neauveaute
    ];
    return response()->json(['data',$stats],200);
    }
}
