import { Routes, RouterModule } from "@angular/router";
import { ActiveUiComponent } from "src/app/ui-components/active-ui/active-ui.component";


const routes: Routes = [
    { path: '', redirectTo: 'active-ui', pathMatch: 'full' },
    { path: 'flights-ui', component: ActiveUiComponent}
];

export const routing = RouterModule.forRoot(routes, { useHash: true } );