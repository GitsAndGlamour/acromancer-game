import angular from 'angular'
import uirouter from 'angular-ui-router';
import sanitize from 'angular-sanitize';
import ngResource from 'angular-resource'
import ngCookies from 'angular-cookies'

import './common/css/application.css';
import {intercept, stateChangeSuccess, stateChangeStart, routing} from './app.config'
import common from './component/common';
import profile from './component/profile';
import bsTracker from './component/directives/bsHttpRequestTracker';
import homeComponent from './component/home';

const commonModule = angular.module('smlAcromancer.common', [ngCookies]);
common(commonModule);

const profileModule = angular.module('smlAcromancer.profile', [uirouter, ngResource, ngCookies, 'smlAcromancer.common']);
profile(profileModule);

const bsTrackerModule = angular.module('smlAcromancer.bsTracker', []);
bsTracker(bsTrackerModule);

const homeModule = angular.module('smlAcromancer.home', [uirouter]);
homeComponent(homeModule);

require('./component/main')(angular.module('smlAcromancer.main', [uirouter]));

const ngModule = angular.module('smlAcromancer',
    [uirouter, sanitize, 'smlAcromancer.common', 'smlAcromancer.profile', 'smlAcromancer.bsTracker', 'smlAcromancer.home', 'smlAcromancer.main'])
  .config(routing)
  .config(intercept)
  .run(stateChangeStart)
  .run(stateChangeSuccess);
