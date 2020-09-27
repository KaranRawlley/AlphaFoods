import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailrecipeComponent } from './detailrecipe.component';

describe('DetailrecipeComponent', () => {
  let component: DetailrecipeComponent;
  let fixture: ComponentFixture<DetailrecipeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailrecipeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailrecipeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
