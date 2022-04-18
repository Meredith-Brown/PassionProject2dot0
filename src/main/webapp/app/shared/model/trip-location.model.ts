import { ILocation } from 'app/shared/model/location.model';
import { ITrip } from 'app/shared/model/trip.model';

export interface ITripLocation {
  id?: number;
  location?: ILocation | null;
  trip?: ITrip | null;
}

export const defaultValue: Readonly<ITripLocation> = {};
