<?php

namespace App\Http\Controllers\Document;

use App\Http\Controllers\Controller;
use App\Models\Document;
use App\Models\Demande;
use Illuminate\Http\Request;

class DocumentController extends Controller
{
    public function AllDocuments(){
        $documents=Document::get();
        return response()->json($documents);
    }
}
