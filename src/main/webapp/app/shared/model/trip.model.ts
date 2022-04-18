import dayjs from 'dayjs';
import { ITripLocation } from 'app/shared/model/trip-location.model';

export interface ITrip {
  id?: number;
  startDate?: string;
  endDate?: string;
  triplocations?: ITripLocation[] | null;
}

export const defaultValue: Readonly<ITrip> = {};
