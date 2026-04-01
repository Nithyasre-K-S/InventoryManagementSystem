
import { Routes } from '@angular/router';
import { LoginComponent } from './login.component/login.component';
import { RegisterComponent } from './register/register';
import { ProductComponent } from './product/product';
import {AddProductComponent } from './add-product/add-product';
import {EditProductComponent} from './edit-product/edit-product';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'product', component: ProductComponent },
  { path: 'product/add', component: AddProductComponent },
  { path: 'product/edit/:id', component: EditProductComponent }
];
