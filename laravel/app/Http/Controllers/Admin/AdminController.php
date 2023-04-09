<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Models\User;
use Illuminate\Http\Request;

class AdminController extends Controller
{
    public function GetStatistique(){

        $nbr_technicien=User::whereHas('roles',function($query){
            $query->where("Role_name","Technicien")->where('status',1);
        })->count();

        $nbr_chef=User::whereHas('roles',function($query){
            $query->where("Role_name","chefDepartment")->where('status',1);
        })->count();

        $nbr_ens=User::whereHas('roles',function($query){
            $query->where("Role_name","ensignant")->where('status',1);
        })->count();

        $nbr_etudiant=User::whereHas('roles',function($query){
            $query->where("Role_name","etudiant")->where('status',1);
        })->count();

        $nbr_etudiant_refused=User::whereHas('roles',function($query){
            $query->where("Role_name","etudiant")->where('status',2);
        })->count();

        $nbr_etudiant_encours=User::whereHas('roles',function($query){
            $query->where("Role_name","etudiant")->where('status',0);
        })->count();

        $nbr_etudiant_homme=User::whereHas('roles',function($query){
            $query->where("Role_name","etudiant")->where('status',1)->where("sex","Man");
        })->count();

        $nbr_etudiant_femme=User::whereHas('roles',function($query){
            $query->where("Role_name","etudiant")->where('status',1)->where("sex","woman");
        })->count();

        $data["nb_etudiant"]=$nbr_etudiant;
        $data["nb_technicien"]=$nbr_technicien;
        $data['nb_man']=$nbr_etudiant_homme;
        $data['nb_woman']=$nbr_etudiant_femme;
        $data['nb_chef']=$nbr_chef;
        $data['nbr_ens']=$nbr_ens;
        $data['nbr_etudiant_refused']=$nbr_etudiant_refused;
        $data['nbr_etudiant_encours']=$nbr_etudiant_encours;
        return response()->json(['data'=>$data]);

    }
}
