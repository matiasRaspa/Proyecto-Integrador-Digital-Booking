import React, { useState } from 'react';
import './registrar.css'
import { AiOutlineEyeInvisible, AiOutlineEye } from 'react-icons/ai';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import { Link } from 'react-router-dom';


export const RegisterScreen = () => {
  const alerta = "Este campo es obligatorio"
  const [formularioEnviado, cambiarFormularioEnviado] = useState(false);
  const [mostrarContrasena, cambiarmostrarContrasena] = useState(false);
  const [nombre, setNombre] = useState(false);
  const [apellido, setApellido] = useState(false);
  const [email, setEmail] = useState(false);
  const [password, setPassword] = useState(false);
  const [confirmPassword, setConfirmPassword] = useState(false);

  const toggleBtn = () => {
    cambiarmostrarContrasena(prevState => !prevState)
  }

  return (
    <div className='contenedor'>
      <Formik
        initialValues={{
          nombre: '',
          apellido: '',
          email: '',
          password: '',
          confirmPassword: ''
        }
        }
        validate={(valores) => {
          let errores = {};
          //validacion de nombre
          if (!valores.nombre) {
            errores.nombre = alerta;
            setNombre("false");
          } else if (!/^[a-zA-ZÀ-ÿ\s]{1,40}$/.test(valores.nombre)) {
            errores.nombre = 'El nombre solo puede contener letras y espacios'
            setNombre("false");
          }
          else setNombre("true");

          //validacion de apellido
          if (!valores.apellido) {
            errores.apellido = alerta;
            setApellido("false");
          } else if (!/^[a-zA-ZÀ-ÿ\s]{1,40}$/.test(valores.apellido)) {
            errores.apellido = 'El apellido solo puede contener letras y espacios';
            setApellido("false");
          }
          else setApellido("true");


          //Validación email
          if (!valores.email) {
            errores.email = alerta;
            setEmail("false");
          } else if (!/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test(valores.email)) {
            // errores.email = 'El correo solo puede contener letras, numeros, puntos, guiones y guion bajo.'
            errores.email = 'Ingrese un correo válido'
            setEmail("false");
          }
          else setEmail("true");

          //Validación password
          if (!valores.password) {
            errores.password = alerta;
            setPassword("false");
          }
          //else if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])([A-Za-z\d$@$!%*?&]|[^ ]){6,15}$/.test(valores.password)) {
          else if (valores.password.length < 6) {
            errores.password = 'La contraseña debe tener por lo menos 6 caracteres';
            setPassword("false");
          }
          else setPassword("true");

          //Confirmar password
          if (!valores.confirmPassword) {
            errores.confirmPassword = alerta;
            setConfirmPassword("false");
          }
          else if (valores.confirmPassword !== valores.password) {
            errores.confirmPassword = "Las contraseñas no coinciden";
            setConfirmPassword("false");
          }
          else setConfirmPassword("true");

          return errores;
        }}

        onSubmit={(valores, { resetForm }) => {
          resetForm();
          cambiarFormularioEnviado(true);
        }}
      >
        {({ errors }) => (
          <Form className='formulario' >
            <p className='titulo-form'>Crear cuenta</p>

            <div className='nombre-completo '>
              <div className='input-nombre'>
                <label htmlFor="nombre">Nombre</label>
                <Field className={`field-nombre-completo ${nombre === 'false' && "error-input"}`}
                  type="text"
                  id="nombre"
                  name='nombre'
                  placeholder=''
                />
                <ErrorMessage name='nombre' component={() => (
                  <div className='error'>{errors.nombre}</div>
                )} />
              </div>

              <div className='input-apellido '>
                <label htmlFor="apellido">Apellido</label>
                <Field className={`field-nombre-completo ${apellido === 'false' && "error-input"}`}
                  type="text"
                  id="apellido"
                  name='apellido'
                  placeholder=''
                />
                <ErrorMessage name='apellido' component={() => (
                  <div className='error'>{errors.apellido}</div>
                )} />
              </div>
            </div>

            <div className='input-email container-input'>

              <label htmlFor="email">Correo electrónico</label>
              <Field className={email === 'false' && "error-input"}
                type="email"
                id="email"
                name='email'
                placeholder=''
              />
              <ErrorMessage name='email' component={() => (
                <div className='error'>{errors.email}</div>
              )} />
            </div>

            <div className='input-password container-input'>

              <label htmlFor="password">Contraseña</label>
              <div className='password-field'>
                <span className='btn' onClick={toggleBtn}>
                  {
                    mostrarContrasena ? <AiOutlineEyeInvisible className='icon-eye' /> : <AiOutlineEye className='icon-eye' />
                  }
                </span>
                <Field className={password === 'false' && "error-input"}
                  type={mostrarContrasena ? "text" : "password"}
                  id="password"
                  name='password'
                  placeholder=''
                  autoComplete="off"
                />
              </div>
              <ErrorMessage name='password' component={() => (
                <div className='error'>{errors.password}</div>
              )} />
            </div>

            <div className='input-confirmPassword container-input'>

              <label htmlFor="confirmPassword">Confirmar contraseña</label>
              <div className='password-field'>
                <Field className={confirmPassword === 'false' && "error-input"}
                  type="password"
                  id="confirmPassword"
                  name='confirmPassword'
                  placeholder=''
                  autoComplete="off"
                />
              </div>
              <ErrorMessage name='confirmPassword' component={() => (
                <div className='error'>{errors.confirmPassword}</div>
              )} />
            </div>
            <button type='submit' >Crear cuenta</button>
            <p>¿Ya tienes una cuenta?
              <Link to={'/login'}>
                Iniciar sesión
              </Link>
            </p>
          </Form>
        )}
      </Formik>
    </div>
  );

}
