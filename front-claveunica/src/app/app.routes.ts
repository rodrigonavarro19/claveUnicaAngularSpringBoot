import { Routes } from '@angular/router';

export const routes: Routes = [
    
    {
        path:'auth',
        loadComponent: () => import('./auth/auth.component'),
        children:[

            {
                path: 'login',
                title: 'Login',
                loadComponent: () => import('./auth/pages/login-clave-unica/login-clave-unica.component'), 
            },
            {
                path: 'callback',
                title: 'Callback clave unica',
                loadComponent: () => import('./auth/pages/callback-clave-unica/callback-clave-unica.component'), 
            },
            {
                path:'**',
                redirectTo:'login',
                
            }

        ],
        
    },
    
    {
        path:'',
        redirectTo:'/auth',
        pathMatch: 'full',
    }
];
