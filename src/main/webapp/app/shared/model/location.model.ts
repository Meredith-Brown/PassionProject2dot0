import { ITripLocation } from 'app/shared/model/trip-location.model';

export interface ILocation {
  id?: number;
  name?: string;
  streetAddress?: string;
  postalCode?: string;
  city?: string;
  stateProvince?: string;
  country?: string;
  visited?: boolean;
  triplocations?: ITripLocation[] | null;
}

export const defaultValue: Readonly<ILocation> = {
  visited: false,
};
