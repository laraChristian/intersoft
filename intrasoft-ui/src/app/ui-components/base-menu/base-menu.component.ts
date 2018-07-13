import { Component, OnInit } from '@angular/core';
import { BaseMenuModel } from 'src/app/ui-components/base-menu/base-menu.model';
import { MenuItem } from 'primeng/primeng';


@Component({
  selector: 'base-menu',
  templateUrl: './base-menu.component.html',
})
export class BaseMenuComponent implements OnInit {

  private model: BaseMenuModel;
  private items: MenuItem[];

  constructor() { 
    this.model = new BaseMenuModel();
  }

  ngOnInit() {
    this.model._items = [
      { label: 'Actives', icon: 'fa fa-fw fa-book', items:[
        { label: 'Active', icon: 'fa fa-fw fa-book', routerLink:'active-ui'}
      ]}
    ];
  }

}
