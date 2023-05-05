<?php

namespace App\Http\Controllers;

use App\Models\User;
use Barryvdh\DomPDF\PDF;

class PdfController extends Controller 
{
    public function generatePDF()
    {
    $users= User::all();
     $data=[
        'title'=>"hhf",
        'user'=>$users
     ];
        $pdf = app()->make(PDF::class);
        $pdf->loadView('pdf.document',$data);
        return $pdf->download('pdf_file.pdf');
    }
}
