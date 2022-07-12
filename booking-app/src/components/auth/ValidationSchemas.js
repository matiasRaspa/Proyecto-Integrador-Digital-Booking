import * as Yup from "yup";


const passwordRulesLower = /[a-z]/;
const passwordRulesUpper = /[A-Z]/;
const passwordRulesNumber = /\d/;
const rulesPhoneNumber = /^[0-9]{7,15}$/gm;


export const SchemaRegisterForm = Yup.object().shape({
  name: Yup.string()
    .min(2, "Demasiado corto")
    .max(50, "Excede los caracteres")
    .required("Este campo es obligatorio"),
  lastname: Yup.string()
    .min(2, "Demasiado corto")
    .max(50, "Excede los caracteres")
    .required("Este campo es obligatorio"),
  email: Yup.string()
    .email("Por favor ingrese un correo electrónico válido")
    .required("Este campo es obligatorio"),
  phone: Yup.string()
    .min(7, "Demasiado corto")
    .matches(rulesPhoneNumber, { message: "Por favor ingrese un número de teléfono válido" })
    .required("Este campo es obligatorio"),
  password: Yup.string()
    .min(6, "La contraseña debe contener mínimo 6 carácteres")
    .matches(passwordRulesLower, { message: "La contraseña debe contener al menos una minúscula" })
    .matches(passwordRulesUpper, { message: "La contraseña debe contener al menos una mayúscula" })
    .matches(passwordRulesNumber, { message: "La contraseña debe contener al menos un número" })
    .required("Este campo es obligatorio"),
  confirmPassword: Yup.string()
    .min(6, "Las contraseñas deben coincidir")
    .oneOf([Yup.ref("password"), null], "Las contraseñas deben coincidir")
    .required("Este campo es obligatorio"),
});

export const SchemaLoginForm = Yup.object().shape({
  email: Yup.string()
    .email("Por favor ingrese un correo electrónico válido")
    .required("Este campo es obligatorio"),
  password: Yup.string()
    .required("Este campo es obligatorio"),
});

export const advancedSchema = Yup.object().shape({
  username: Yup
    .string()
    .min(3, "Username must be at least 3 characters long")
    .required("Required"),
  jobType: Yup
    .string()
    .oneOf(["designer", "developer", "manager", "other"], "Invalid Job Type")
    .required("Required"),
  acceptedTos: Yup
    .boolean()
    .oneOf([true], "Please accept the terms of service"),
});