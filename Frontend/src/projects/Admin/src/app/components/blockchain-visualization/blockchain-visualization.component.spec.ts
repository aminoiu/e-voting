import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BlockchainVisualizationComponent } from './blockchain-visualization.component';

describe('BlockchainVisualizationComponent', () => {
  let component: BlockchainVisualizationComponent;
  let fixture: ComponentFixture<BlockchainVisualizationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BlockchainVisualizationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BlockchainVisualizationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
