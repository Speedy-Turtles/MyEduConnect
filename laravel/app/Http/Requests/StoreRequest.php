<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Http\Exceptions\HttpResponseException;
use Illuminate\Contracts\Validation\Validator;
class StoreRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     */
    public function authorize(): bool
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array<string, \Illuminate\Contracts\Validation\Rule|array|string>
     */
    public function rules(): array
    {
        $sevenYearsAgo=date("Y-m-d",strtotime("-70 year"));
        $nineteenYearsAgo=date("Y-m-d",strtotime("-19 year"));
        return [
            "lastname"=>"required|max:10|min:3",
            "firstname"=>"required|max:10|min:3",
            "email"=>"required|email|unique:users",
            "password"=>"required|max:10|min:6",
          //  "photo"=>"max:255|mimes:jpg,png,svg,gif",
            "cin"=>"required",
            "sex"=>"required",
            "birth_day"=>'required|date|before:'.$nineteenYearsAgo.'|after:'.$sevenYearsAgo,
            "num_tlf"=>"required|digits:8|numeric"
        ];
    }

    public function failedValidation(Validator $validator)
    {
        throw new HttpResponseException(response()->json([
            'success'   => false,
            'message'   => 'Validation errors',
            'data'      => $validator->errors()
        ],400));
    }
}
